package com.luanvv.spring.springstructure.controllers.stock;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.base.Preconditions;
import com.luanvv.spring.springstructure.controllers.base.BaseController;
import com.luanvv.spring.springstructure.entities.Stock;
import com.luanvv.spring.springstructure.exeptions.MyResourceNotFoundException;
import com.luanvv.spring.springstructure.responses.StockDTO;
import com.luanvv.spring.springstructure.services.StockService;
import com.luanvv.spring.springstructure.validators.StockValidator;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class StockController extends BaseController {
	
	@Autowired
	private StockService stockService;

	@Autowired
    private ModelMapper modelMapper;
	
	@RequestMapping(value = "/stock/{id}", method = RequestMethod.GET)
	public Resource<StockDTO> get(@PathVariable("id") String stockId,  final HttpServletResponse response) {
		log.debug("Say something....");
		Stock stock = stockService.findOne(stockId).orElseThrow(MyResourceNotFoundException::new);
		stock.setStockDailyRecords(Collections.emptySet());
		Resource<StockDTO> resource = new Resource<>(convertToDTO(stock));
		ControllerLinkBuilder linkTo = linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAll());
		resource.add(linkTo.withRel("all-stocks"));
		return resource;
	}

	private StockDTO convertToDTO(Stock stock) {
		return modelMapper.map(stock, StockDTO.class);
	}
	
	@RequestMapping(value="/stock", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Resource<String> create(@Valid @RequestBody final Stock stock, 
    		final HttpServletResponse response) {
        Preconditions.checkNotNull(stock);
        final Stock newStock = stockService.create(stock);
        final String idOfCreatedResource = newStock.getUuid().toString();
        Link selfRel = linkTo(ControllerLinkBuilder.methodOn(this.getClass())
				.get(idOfCreatedResource, response))
				.withSelfRel();
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(newStock.getUuid().toString()).toUri();
//
//        return ResponseEntity.created(location).build();
		return new Resource<>(idOfCreatedResource, selfRel);
    }
	
	@DeleteMapping("/stock/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> delete(@PathVariable String id) {
		stockService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/stock/list", params = { "page", "size" }, method = RequestMethod.GET)
	public Resources<StockDTO> findPaginated(@RequestParam("page") final int page, @RequestParam("size") final int size,
			final UriComponentsBuilder uriBuilder,
			final HttpServletResponse response) {
		Page<Stock> resultPage = stockService.findPaginated(page, size);
		if (page > resultPage.getTotalPages()) {
			throw new MyResourceNotFoundException();
		}
        List<StockDTO> stockDtoes = resultPage.getContent()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
        Resources<StockDTO> resource = new Resources<>(stockDtoes);
        if(resultPage.hasNext()) {
        	Link link = linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findPaginated(page + 1, size, uriBuilder, response))
        			.withRel("next");
        	resource.add(link);
        }
		return resource;
	}

	@RequestMapping(value = "/stock/all", method = RequestMethod.GET)
	public List<StockDTO> findAll() {
		return stockService.findAll().stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new StockValidator());
    }
}
