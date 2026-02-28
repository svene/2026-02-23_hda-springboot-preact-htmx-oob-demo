import {render, h} from "preact";
import {OOBPersonDetailModel} from "../inbound/web/vm/oob-person-page-model-vm";

export const PersondetailsRow = (props: { vm: OOBPersonDetailModel }) => (
		<>
			<tr
				id={`row-${props.vm.id}`}
				style="cursor: pointer"
				hx-trigger="click"
				hx-target="this"
				hx-swap="outerHTML transition:true"
				hx-get={props.vm._rowUrl}
			>
				<td style="border-style: none"></td>
				<td style="border-style: none">{props.vm.firstName}</td>
				<td style="border-style: none">{props.vm.lastName}</td>
				<td style="border-style: none">{props.vm.streetName}</td>
				<td style="border-style: none"><span className="icon"><i className="material-icons">arrow_drop_up</i></span></td>
			</tr>
		</>
);
export {render, h, PersondetailsRow as Cmp}
