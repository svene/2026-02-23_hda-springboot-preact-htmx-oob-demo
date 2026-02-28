import {render, h} from "preact";
import {OOBPersonEditModel} from "../inbound/web/vm/oob-person-page-model-vm";

export const PersonEditor = (props: { vm: OOBPersonEditModel }) => (
	<tr id={`row-${props.vm.id}-edit`}>
		<td colSpan={4} style="padding: 0px">
			<div class="card p-5 my-2">
				<form>
					<div class="fixed-grid">
						<div class="grid">
							<div class="cell">
								<div class="field">
									<label class="label">Firstname</label>
									<div class="control">
										<input class="input" type="text" name="firstName" value={props.vm.firstName}></input>
									</div>
								</div>
							</div>
							<div class="cell">
								<div class="field">
									<label class="label">Lastname</label>
									<div class="control">
										<input class="input" type="text" name="lastName" value={props.vm.lastName}></input>
									</div>
								</div>
							</div>
							<div class="cell">
								<div class="field">
									<label class="label">Street</label>
									<div class="control">
										<input class="input" type="text" name="streetName" value={props.vm.streetName}></input>
									</div>
								</div>
							</div>
						</div>
					</div>
					<nav class="level">
						<button
							class="level-item button"
							hx-trigger="click consume"
							hx-target="closest tr"
							hx-swap="outerHTML"
							hx-get={props.vm._editBackLink}
						>&lt; Back
						</button>
						{/* TODO: can this hx-* stuff be put on form above? */}
						<button
							type="submit"
							class="level-item button is-primary"
							hx-trigger="click consume"
							hx-put={`${props.vm._submitLink}`}
							hx-target="closest tr"
							hx-swap="outerHTML"
						>Save
						</button>
					</nav>
				</form>
			</div>
		</td>
	</tr>
);
export {render, h, PersonEditor as Cmp}
