import {render, h} from "preact";
import {OOBPersonTableModel} from "../inbound/web/vm/oob-person-page-model-vm";
import {OOBHonoWebApiConsts} from "./oob-hono-web-api-shared-consts";
import {PersonRow} from "./personrow";

export const PersonTable = (props: { vm: OOBPersonTableModel }) => (
	<div id="result-table">
		<table className="table">
			<thead>
			<tr>
				<td colSpan={5}>
					<form id="bulkDeleteForm" hx-delete={props.vm._deleteLink}>
						<button type="submit" className="button">
							<span className="icon"><i className="material-icons">delete</i></span>
							<span>Delete</span>
						</button>
					</form>
				</td>
			</tr>
			<tr>
				<th></th>
				<th>Firstname</th>
				<th>Lastname</th>
				<th>Street</th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			{props.vm.people.map((it) => (<PersonRow vm={it}/>))}
			</tbody>
		</table>
		<div>{props.vm.people.length} of total {props.vm.total}</div>

	</div>
);
export {render, h, PersonTable as Cmp}
