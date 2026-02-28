package org.svenehrke.demo.inbound.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.svenehrke.demo.core.PeopleService;
import tools.jackson.databind.json.JsonMapper;

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

		var vm = new OOBPersonPageModel(
			peopleService.personTableModel(),
			RouteBuilder.url(RouteBuilder.PERSON_TABLE_URL)
		);
		model.addAttribute("vm", makeVM(vm));
		return "pages/page1";
	}

	@GetMapping(RouteBuilder.DETAILS_URL)
	public String details(@PathVariable int id, Model model) {
		OOBPersonDetailModel vm = peopleService.personDetailModel(id);
		model.addAttribute("cmpName", "persondetails");
		model.addAttribute("vm", makeVM(vm));
		return "pages/tr";
	}

	@GetMapping(RouteBuilder.DETAILS_BACK_URL)
	public String detailsBack(@PathVariable int id, Model model) {
		OOBPersonTableRowModel vm = peopleService.personTableRowModel(id);
		model.addAttribute("cmpName", "persondetailsback");
		model.addAttribute("vm", makeVM(vm));
		return "pages/tr";
	}

	@GetMapping(RouteBuilder.EDIT_URL)
	public String edit(@PathVariable int id, Model model) {
		OOBPersonEditModel vm = peopleService.personEditModel(id);
		model.addAttribute("cmpName", "personedit");
		model.addAttribute("vm", makeVM(vm));
		return "pages/tr";
	}

	private String makeVM(Object vm) {
		record VMWrapper(Object vm) { }
		return jsonMapper.writeValueAsString(new VMWrapper(vm));
	}

}
