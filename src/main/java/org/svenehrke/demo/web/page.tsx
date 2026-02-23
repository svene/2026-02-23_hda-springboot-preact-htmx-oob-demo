import { render, h } from "preact";
import {OOBPersonPageModel} from "../inbound/web/vm/oob-person-page-model-vm";

function App(props: {vm: OOBPersonPageModel}) {
	return (
		<>
		<div>total: {props.vm.table.total}</div>
		<div>firstname: {props.vm.table.people.at(0)?.firstName}</div>
		</>
	)
		;
}

export {render, h, App}
