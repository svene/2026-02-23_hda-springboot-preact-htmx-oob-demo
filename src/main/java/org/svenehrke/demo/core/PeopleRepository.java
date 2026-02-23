package org.svenehrke.demo.core;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.svenehrke.demo.inbound.web.OOBPersonDetailModel;
import org.svenehrke.demo.inbound.web.OOBPersonEditModel;
import org.svenehrke.demo.inbound.web.OOBPersonTableModel;
import org.svenehrke.demo.inbound.web.OOBPersonTableRowModel;

import java.util.List;

public interface PeopleRepository {
    OOBPersonTableModel people();
    OOBPersonTableModel peopleForSearch(String search);
    int total();
    OOBPersonTableRowModel personTableRowModel(int id);
    OOBPersonEditModel personEditModel(int id);
    OOBPersonDetailModel personDetailModel(int id);
    int deleteByIds(List<Integer> ids);
    int updatePerson(int id, OOBPersonEditModel personEditModel);
}
