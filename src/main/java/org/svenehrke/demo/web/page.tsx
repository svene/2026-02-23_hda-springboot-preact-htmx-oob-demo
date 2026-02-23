import {render, h} from "preact";
import {OOBPersonPageModel} from "../inbound/web/vm/oob-person-page-model-vm";
import {PersonTable} from "./persontable";

function App(props: { vm: OOBPersonPageModel }) {
	return (
		<>
			<div className="container mt-1">
				<div className="p-1 mt-1 area-border" style="min-height: 500px">

					<div className="field">
						<label className="label">Search</label>
						<div className="control">
							<input
								className="input"
								type="search"
								name="search"
								placeholder="Search for firstname or lastname"
								hx-trigger="input changed delay:500ms"
								hx-get={props.vm._tableLink}
								hx-target="#result-table"
							/>
						</div>
					</div>
					<PersonTable vm={props.vm.table}></PersonTable>

				</div>

			</div>
		</>
	)
		;
}

export {render, h, App}
