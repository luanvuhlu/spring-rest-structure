import os
import shutil
import sys

target_dir = 'target/classes'
prrefix = 'src/main/resources/'

# Get the file path from the command line arguments
file_path = sys.argv[1]
# remove prefix src\main\resources\ from file_path
target_file_path = file_path[len(prrefix):]
# Check if the file exists in the source directory
if not os.path.exists(file_path):
    print('File does not exist in the source directory.')
    sys.exit()

# Get the directory of the input file
file_dir = os.path.dirname(target_file_path)

# Create the target subdirectory if it doesn't exist
os.makedirs(os.path.join(target_dir, file_dir), exist_ok=True)

# Copy the file to the target directory
shutil.copy(file_path, os.path.join(target_dir, file_dir))
print('Copied File from', file_path, 'to', os.path.join(target_dir, file_dir))