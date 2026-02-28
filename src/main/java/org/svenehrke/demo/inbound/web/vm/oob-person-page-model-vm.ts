export type OOBPersonTableRowModel = {
	id: number,
	firstName: string,
	lastName: string,
	streetName: string,
	_detailsLink: string,
}

export type OOBPersonTableModel = {
	people: OOBPersonTableRowModel[],
	total: number,
}
export type OOBPersonPageModel = {
	table: OOBPersonTableModel,
	_tableLink: string,
}

export type OOBPersonDetailModel = {
	id: number,
	firstName: string,
	lastName: string,
	streetName: string,
	streetNo: string,
	zipCode: string,
	city: string,
	country: string,
	mailBox: string,
	phoneNumber: string,
	cellPhone: string,
	_editLink: string,
	_rowUrl: string,
}

export type OOBPersonEditModel = {
	id: number,
	firstName: string,
	lastName: string,
	streetName: string,
	_editBackLink: string,
	_submitLink: string,
}

