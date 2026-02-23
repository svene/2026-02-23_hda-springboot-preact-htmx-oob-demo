package org.svenehrke.demo.core;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.svenehrke.demo.inbound.web.OOBPersonDetailModel;
import org.svenehrke.demo.inbound.web.OOBPersonEditModel;
import org.svenehrke.demo.inbound.web.OOBPersonTableModel;
import org.svenehrke.demo.inbound.web.OOBPersonTableRowModel;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

	public PeopleService(PeopleRepository peopleRepository) {
		this.peopleRepository = peopleRepository;
	}

	public OOBPersonTableModel personTableModel() {
        return peopleRepository.people();
    }

	public OOBPersonTableModel peopleForSearch(String search) {
        return StringUtils.hasLength(search) ? peopleRepository.peopleForSearch(search) : peopleRepository.people();
    }

	public int total() {
        return peopleRepository.total();
    }

	public OOBPersonTableRowModel personTableRowModel(int id) {
		return peopleRepository.personTableRowModel(id);
	}
	public OOBPersonEditModel personEditModel(int id) {
		return peopleRepository.personEditModel(id);
	}

	public OOBPersonDetailModel personDetailModel(int id) {
		return peopleRepository.personDetailModel(id);
	}

	@Transactional
	public int deleteByIds(List<Integer> ids) {
		return peopleRepository.deleteByIds(ids);
	}

	@Transactional
	public int updatePerson(int id, OOBPersonEditModel personEditModel) {
		return peopleRepository.updatePerson(id, personEditModel);
	}

}
