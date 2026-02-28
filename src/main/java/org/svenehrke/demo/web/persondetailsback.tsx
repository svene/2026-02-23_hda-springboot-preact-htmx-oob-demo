import {render, h} from "preact";
import {OOBPersonTableRowModel} from "../inbound/web/vm/oob-person-page-model-vm";
import {PersonRow} from "./pesonrow";

export const PersonDetailsBack = (props: { vm: OOBPersonTableRowModel }) => (
		<>
			<PersonRow vm={props.vm}/>

{/*
			NOTE: OOB seems not to work with the preact approach.
			I _think_ it is because oob is processed by htmx for a http response.
			But since the oob attributes are created on the client by preact they will just be ignored.
			Therefore events are used in this app to achieve the desired behavior.
*/}
{/*
			<tr id={`row-${props.vm.id}-details`} hx-swap-oob="outerHTML">
			</tr>
*/}

		</>
);
export {render, h, PersonDetailsBack as Cmp}
