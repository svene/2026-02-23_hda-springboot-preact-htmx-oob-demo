import {render, h} from "preact";
import {OOBPersonPageModel} from "../inbound/web/vm/oob-person-page-model-vm";
import {PersonTable} from "./persontable";

import { useSignal } from '@preact/signals';


function Cmp(props: { vm: OOBPersonPageModel }) {
	const counter = useSignal(0);
	return (
		<>
			<div class="container mt-1">
				<div class="p-1 mt-1 area-border" style="min-height: 500px">

					<div class="field">
						<label class="label">Search</label>
						<div class="control">
							<input
								class="input"
								type="search"
								name="search"
								placeholder="Search for firstname or lastname"
								hx-trigger="input changed delay:500ms"
								hx-get={props.vm._tableLink}
								hx-target="#result-table"
							/>
						</div>
					</div>
					<div>{counter}</div>
					<button class="button" onClick={e => counter.value = counter.value + 1}>Inc</button>
					<PersonTable vm={props.vm.table}></PersonTable>

				</div>

			</div>
		</>
	)
		;
}

export {render, h, Cmp}
