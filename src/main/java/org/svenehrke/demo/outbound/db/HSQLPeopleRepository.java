package org.svenehrke.demo.outbound.db;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.svenehrke.demo.core.PeopleRepository;
import org.svenehrke.demo.inbound.web.*;

import java.util.List;

@Repository
@Primary
public class HSQLPeopleRepository implements PeopleRepository {

	private final JdbcClient jdbcClient;
	private final JdbcTemplate jdbcTemplate;

	public HSQLPeopleRepository(
		JdbcClient jdbcClient,
		JdbcTemplate jdbcTemplate
	) {
		this.jdbcClient = jdbcClient;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public OOBPersonTableModel people() {
		var sql = "select id, firstname, lastname, streetname from Person limit 20";
		List<OOBPersonTableRowModel> result = jdbcClient.sql(sql).query(
			(rs, rowNum) -> new OOBPersonTableRowModel(
				rs.getInt("id"),
				rs.getString("firstname"),
				rs.getString("lastname"),
				rs.getString("streetname"),
				RouteBuilder.detailsUrl(rs.getInt("id"))
		)).list();
		return new OOBPersonTableModel(result, total());
	}

	@Override
	public OOBPersonTableModel peopleForSearch(String search) {
		var sql = """
			select id, firstname, lastname, streetname
			from Person
			where
				firstname like (:search)
				or lastname like (:search)
				or streetname like (:search)
			limit 20
			""";
		List<OOBPersonTableRowModel> result = jdbcClient.sql(sql)
			.param("search", "%" + search + "%")
			.query(
			(rs, rowNum) -> new OOBPersonTableRowModel(
				rs.getInt("id"),
				rs.getString("firstname"),
				rs.getString("lastname"),
				rs.getString("streetname"),
				RouteBuilder.detailsUrl(rs.getInt("id"))
			)).list();
		return new OOBPersonTableModel(result, total());
	}

	@Override
	public int total() {
		Integer count = jdbcTemplate.queryForObject(
			"SELECT COUNT(*) FROM Person",
			Integer.class
		);
		return count == null ? 0 : count;
	}

	@Override
	public OOBPersonTableRowModel personTableRowModel(int id) {
		var sql = "select id, firstname, lastname, streetname from Person where id = ?";
		OOBPersonTableRowModel result = jdbcClient.sql(sql)
			.param(id)
			.query(
			(rs, rowNum) -> new OOBPersonTableRowModel(
				rs.getInt("id"),
				rs.getString("firstname"),
				rs.getString("lastname"),
				rs.getString("streetname"),
				RouteBuilder.detailsUrl(rs.getInt("id"))
			)).single();
		return result;
	}

	@Override
	public OOBPersonEditModel personEditModel(int id) {
		var sql = "select id, firstname, lastname, streetname from Person where id = ?";
		return jdbcClient.sql(sql)
			.param(id)
			.query(
				(rs, rowNum) -> new OOBPersonEditModel(
					rs.getInt("id"),
					rs.getString("firstname"),
					rs.getString("lastname"),
					rs.getString("streetname"),
					RouteBuilder.detailsCardUrl(rs.getInt("id")),
					RouteBuilder.updateUrl(rs.getInt("id"))
				)).single();
	}

	@Override
	public OOBPersonDetailModel personDetailModel(int id) {
		var sql = """
			select
				id, firstname, lastname, streetname, streetno, zipcode, city,
			    country, mailbox, phonenumber, cellphone
			from Person
			where id = ?
			""";
		OOBPersonDetailModel result = jdbcClient.sql(sql)
			.param(id)
			.query(
			(rs, rowNum) -> new OOBPersonDetailModel(
				rs.getInt("id"),
				rs.getString("firstname"),
				rs.getString("lastname"),
				rs.getString("streetname"),
				rs.getString("streetno"),
				rs.getString("zipcode"),
				rs.getString("city"),
				rs.getString("country"),
				rs.getString("mailbox"),
				rs.getString("phonenumber"),
				rs.getString("cellphone"),
//				RouteBuilder.detailsBackUrl(rs.getInt("id")),
				RouteBuilder.rowUrl(rs.getInt("id")),
				RouteBuilder.editUrl(rs.getInt("id")),
				RouteBuilder.rowUrl(rs.getInt("id"))
			)
		).single();
		return result;
	}

	@Override
	public int deleteByIds(List<Integer> ids) {
		var sql = "delete from Person where id in (:ids)";
		return jdbcClient.sql(sql).param("ids", ids).update();
	}

	@Override
	public int updatePerson(int id, OOBPersonEditModel personEditModel) {
		var sql = "update Person set firstname = (:firstname), lastname = (:lastname), streetname = (:streetname) where id = (:id)";
		return jdbcClient.sql(sql)
			.param("firstname", personEditModel.firstName())
			.param("lastname", personEditModel.lastName())
			.param("streetname", personEditModel.streetName())
			.param("id", id)
			.update();
	}

}
