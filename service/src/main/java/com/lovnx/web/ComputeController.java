package com.lovnx.web;

import com.lovnx.model.ds.City;
import com.lovnx.model.ws.Score;
import com.lovnx.service.ScoreService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ComputeController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ScoreService ss;

	@Value("${from}")
	private String from;

	@RequestMapping("/from")
	public String from() {
		return this.from;
	}

	/*@RequestMapping(value = "/find", method = RequestMethod.GET)
	public List<City> find(String name, Integer pageNum, Integer pageSize) {
		Sort sort = new Sort(Direction.DESC, "id");
		Pageable page = new PageRequest(pageNum, pageSize, sort);
		return cs.findByNameLike("%" + name, page);
	}*/

	@RequestMapping(value = "/findAllScore", method = RequestMethod.POST)
	public List<Score> findAllScore() {
		return ss.findAll();
	}

	@RequestMapping(value = "/saveScore", method = RequestMethod.POST)
	public String saveScore(@RequestBody  Score score) {
		return ss.save(score).toString();
	}

}