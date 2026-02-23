package org.svenehrke.demo.inbound.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.svenehrke.demo.core.PeopleService;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

@Controller
public class PagesController {

	private final JsonMapper jsonMapper;
	private final PeopleService peopleService;

	@Value("${spring.profiles.active:}")
	private String activeProfile;

	public PagesController(JsonMapper jsonMapper, PeopleService peopleService) {
		this.jsonMapper = jsonMapper;
		this.peopleService = peopleService;
	}

	@GetMapping("/")
	public String redirectRoot() {
		return "redirect:" + RouteBuilder.PAGE_URL;
	}

	@GetMapping(RouteBuilder.PAGE_URL)
	public String page1(Model model) {
		model.addAttribute("devMode", activeProfile.contains("dev"));
		model.addAttribute("greetee", "You");
		record JJJ(String message) {
		}
		;
		model.addAttribute("jjj", jsonMapper.writeValueAsString(
			new JJJ("myJJJ")
		));
		var vm = new OOBPersonPageModel(
			peopleService.personTableModel(),
			RouteBuilder.url(RouteBuilder.PERSON_TABLE_URL)
		);

		model.addAttribute("vm", vm);
		return "pages/page1";
	}

}
