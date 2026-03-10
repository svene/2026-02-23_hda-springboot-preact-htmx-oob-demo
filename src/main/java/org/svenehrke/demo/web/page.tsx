import {render, h} from "preact";
import {PersonPageModel} from "../inbound/web/vm/person-page-model-vm";
import {PersonTable} from "./persontable";

import { useSignal } from '@preact/signals';
import {HonoWebApiConsts} from "./hono-web-api-shared-consts";


function Cmp(props: { vm: PersonPageModel }) {
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
								hx-get={HonoWebApiConsts.PERSON_TABLE}
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
