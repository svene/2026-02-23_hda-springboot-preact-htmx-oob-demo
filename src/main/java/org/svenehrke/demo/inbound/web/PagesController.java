package org.svenehrke.demo.inbound.web;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.svenehrke.demo.core.PeopleService;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

import static org.svenehrke.demo.inbound.web.HTMXConsts.HX_REDIRECT;

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

	@GetMapping(RouteBuilder.ROW_URL)
	public String row(@PathVariable int id, Model model) {
		OOBPersonTableRowModel vm = peopleService.personTableRowModel(id);
		model.addAttribute("cmpName", "personrow");
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

	@GetMapping(RouteBuilder.DETAILS_CARD_URL)
	public String detailsCard(@PathVariable int id, Model model) {
		OOBPersonDetailModel vm = peopleService.personDetailModel(id);
		model.addAttribute("cmpName", "persondetailscard");
		model.addAttribute("vm", makeVM(vm));
		return "pages/tr";
	}

	@PutMapping(RouteBuilder.PERSON_URL)
	public void updatePerson(@PathVariable int id, OOBPersonEditModel personEditModel, HttpServletResponse response) {
		peopleService.updatePerson(id, personEditModel);
		response.setHeader(HTMXConsts.HX_TRIGGER, """
			{"%s": {"id": %d}}\
			""".formatted(OobHonoWebApiSharedConsts.EvtBackendEvents.PERSON_UPDATED, id));
	}
	@GetMapping(RouteBuilder.DETAILS_ROW_URL)
	public String detailsRow(@PathVariable int id, Model model) {
		OOBPersonDetailModel vm = peopleService.personDetailModel(id);
		model.addAttribute("cmpName", "persondetailrow");
		model.addAttribute("vm", makeVM(vm));
		return "pages/tr";
	}

	@GetMapping(RouteBuilder.PERSON_TABLE_URL)
	public String peopleUrl(@RequestParam() String search, Model model) {
		OOBPersonTableModel vm = peopleService.peopleForSearch(search);
		model.addAttribute("cmpName", "persontable");
		model.addAttribute("vm", makeVM(vm));
		return "pages/div";
	}
	@DeleteMapping(RouteBuilder.DELETE_URL)
	public void deleteRows(@RequestParam List<Integer> selection, HttpServletResponse response) {
		peopleService.deleteByIds(selection);
		response.setHeader(HX_REDIRECT, RouteBuilder.PAGE_URL);
	}

	private String makeVM(Object vm) {
		record VMWrapper(Object vm) { }
		return jsonMapper.writeValueAsString(new VMWrapper(vm));
	}
}
