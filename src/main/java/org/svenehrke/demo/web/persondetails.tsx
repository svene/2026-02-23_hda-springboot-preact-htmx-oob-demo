import {render, h} from "preact";
import {PersondetailsRow} from "./persondetailrow";
import {PersondetailsCard} from "./persondetailscard";
import {PersonDetailModel} from "../inbound/web/vm/person-page-model-vm";

export const PersonDetails = (props: { vm: PersonDetailModel }) => (
	<>
		<PersondetailsRow vm={props.vm}/>
		<PersondetailsCard vm={props.vm}/>
	</>
);
export {render, h, PersonDetails as Cmp}
