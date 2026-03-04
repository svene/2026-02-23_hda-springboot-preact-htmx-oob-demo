import {render, h} from "preact";
import {OOBPersonDetailModel} from "../inbound/web/vm/oob-person-page-model-vm";
import {EvtBackendEvents} from "./oob-hono-web-api-shared-consts";

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
				<template
					hx-trigger={`
			${EvtBackendEvents.PERSON_UPDATED}[event.detail.id === ${props.vm.id}] from:body
			`}
					hx-target="closest tr"
					hx-swap="outerHTML"
					hx-get={props.vm._detailsRowUrl}
				></template>
				<td style="border-style: none"><input type="checkbox" disabled value={props.vm.id}></input></td>
				<td style="border-style: none">{props.vm.firstName}</td>
				<td style="border-style: none">{props.vm.lastName}</td>
				<td style="border-style: none">{props.vm.streetName}</td>
				<td style="border-style: none"><span class="icon"><i class="material-icons">arrow_drop_up</i></span></td>
			</tr>
		</>
);
export {render, h, PersondetailsRow as Cmp}
