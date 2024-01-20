# -*- coding: utf-8 -*-
"""
    Created by: Luan Vu
    
    This script is used to build and deploy a jar file to a remote server.
    It uses Fabric to run commands on the remote server 
    and uses python-dotenv to load environment variables from a .env file.
    
    Setup:
        Create a new file named .env with the following content:
        SERVER_HOST=<host>
        SUDO_PASSWORD=<password>
    $ pip install fabric python-dotenv
    $ fab -l
    $ fab deploy
    
"""
import os
from fabric import task, Connection
from invoke import run as local
from dotenv import load_dotenv


load_dotenv()
SERVER_HOST = os.getenv('SERVER_HOST')
SUDO_PASSWORD = os.getenv('SUDO_PASSWORD')
SUDO_USER = 'centos'
SERVICE_NAME = 'my_service'
LOCAL_JAR = 'my_service.jar'
REMOTE_JAR = '/var/app/my_service.jar'

def root_conn():
    return Connection(
        host='{0}@{1}'.format(SUDO_USER, SERVER_HOST),
        connect_kwargs={"password": SUDO_PASSWORD}
    )

def run_as_root(c, command):
    if c.user == SUDO_USER:
        command(c)
    else:
        with root_conn() as conn:
            command(conn)

@task
def mem_usage(c):
    '''Check free memory'''
    MEM_CPU_USAGE = 'free -m && top -bn2 | grep "%Cpu" | tail -1 | grep -P "(....|...) id,"|awk "{print \\"CPU Usage: \\" 100-$8 \\"%\\"}"'
    run_as_root(c, lambda c: c.run(MEM_CPU_USAGE))

@task
def restart(c):
    '''Restart service'''
    print("Restarting service")
    run_as_root(c, lambda c: c.sudo("service {0} restart".format(SERVICE_NAME)))

@task
def status(c):
    '''Get service status'''
    print("Getting service status")
    run_as_root(c, lambda c: c.sudo('service {0} status | grep "Active: active"'.format(SERVICE_NAME)))

@task
def upload_jar(c):
    '''Upload jar file to server'''
    print("Uploading jar file to server")
    run_as_root(c, lambda c: c.put(LOCAL_JAR, REMOTE_JAR))

@task
def package(c):
    '''Package jar file'''
    local("mvn clean package")
    
@task
def deploy(c):
    '''Deploy to server'''
    print("Deploying to server")
    package(c)
    with root_conn() as conn:
        upload_jar(conn)
        restart(conn)
        status(conn)