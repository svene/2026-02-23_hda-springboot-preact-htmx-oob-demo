import {render, h} from "preact";
import {OOBPersonTableRowModel} from "../inbound/web/vm/oob-person-page-model-vm";
import {PersonRow} from "./pesonrow";

export const PersonDetailsBack = (props: { vm: OOBPersonTableRowModel }) => (
	<PersonRow vm={props.vm}/>
);
export {render, h, PersonDetailsBack as Cmp}
