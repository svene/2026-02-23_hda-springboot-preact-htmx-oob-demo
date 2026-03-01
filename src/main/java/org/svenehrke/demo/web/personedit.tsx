import {render, h} from "preact";
import {OOBPersonEditModel} from "../inbound/web/vm/oob-person-page-model-vm";
import {EvtBackendEvents} from "./oob-hono-web-api-shared-consts";

export const PersonEditor = ({vm}: { vm: OOBPersonEditModel }) => (
	<tr id={`row-${vm.id}-edit`}>
		<template
			hx-trigger={`
			${EditEvents.CLOSE_REQUESTED}[event.detail.id === ${vm.id}] from:closest tr
			`}
			hx-target="closest tr"
			hx-swap="outerHTML transition:true"
			hx-get={vm._editBackLink}
		></template>
		<template
			hx-trigger={`
			${EvtBackendEvents.PERSON_UPDATED}[event.detail.id === ${vm.id}] from:closest tr
			`}
			hx-target="closest tr"
			hx-swap="outerHTML transition:true"
			hx-get={vm._editBackLink}
		></template>
		<td colSpan={4} style="padding: 0px">
			<div class="card p-5 my-2">
				<form>
					<div class="fixed-grid">
						<div class="grid">
							<div class="cell">
								<div class="field">
									<label class="label">Firstname</label>
									<div class="control">
										<input class="input" type="text" name="firstName" value={vm.firstName}></input>
									</div>
								</div>
							</div>
							<div class="cell">
								<div class="field">
									<label class="label">Lastname</label>
									<div class="control">
										<input class="input" type="text" name="lastName" value={vm.lastName}></input>
									</div>
								</div>
							</div>
							<div class="cell">
								<div class="field">
									<label class="label">Street</label>
									<div class="control">
										<input class="input" type="text" name="streetName" value={vm.streetName}></input>
									</div>
								</div>
							</div>
						</div>
					</div>
					<nav class="level">
						<button
							class="level-item button"
							_={`on click halt the event then send '${EditEvents.CLOSE_REQUESTED}'(id:${vm.id})`}
						>&lt; Back
						</button>
						{/* TODO: can this hx-* stuff be put on form above? */}
						<button
							type="submit"
							class="level-item button is-primary"
							hx-trigger="click consume"
							hx-put={`${vm._submitLink}`} /* Expects backend to respond with 'person-updated'(id) event */
							hx-swap="none" /* Works with event handling of 'person-updated' */
						>Save
						</button>
					</nav>
				</form>
			</div>
		</td>
	</tr>
);

const EditEvents = {
	CLOSE_REQUESTED: 'close-edit-requested',
};

export {render, h, PersonEditor as Cmp}
