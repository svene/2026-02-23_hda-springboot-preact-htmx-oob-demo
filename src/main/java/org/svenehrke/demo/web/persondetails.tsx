import {render, h} from "preact";
import {PersondetailsRow} from "./persondetailrow";
import {PersondetailsCard} from "./persondetailscard";
import {OOBPersonDetailModel} from "../inbound/web/vm/oob-person-page-model-vm";

export const PersonDetails = (props: { vm: OOBPersonDetailModel }) => (
	<>
		<PersondetailsRow vm={props.vm}/>
		<PersondetailsCard vm={props.vm}/>
	</>
);
export {render, h, PersonDetails as Cmp}
