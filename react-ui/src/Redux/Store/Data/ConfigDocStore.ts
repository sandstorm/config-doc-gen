import {ActionsUnion, createAction} from '@martin_hotell/rex-tils';
import {createSelector} from 'reselect';

import {IApplicationState} from '..';
import {ConfigDoc} from "../../../Domain/ConfigDoc";

//
// State
//
export interface IConfigDocState {
    readonly rawApiData: ConfigDoc;
}

const initialState: IConfigDocState = {
    // tslint:disable-next-line:no-string-literal
    rawApiData: {... window["CONFIG_DOC_JSON_DATA"]},
};

//
// Actions
//
export enum ActionTypes {
    FETCH_CONFIG_DOC = '@@config-doc-ui/Data/ConfigDoc/FETCH_CONFIG_DOC',
}

export const actions = {
    fetchConfigDoc: () => createAction(ActionTypes.FETCH_CONFIG_DOC),
};

type EmployeesAction = ActionsUnion<typeof actions>;

//
// Reducer
//
export function reducer(state: IConfigDocState = initialState, action: EmployeesAction): IConfigDocState {
    switch (action.type) {
        case ActionTypes.FETCH_CONFIG_DOC:
        default:
            return state;
    }
}

//
// Selectors
//
const rawDataSelector = (state: IApplicationState) => state.Data.ConfigDoc.rawApiData;
const propertiesSelector = createSelector(rawDataSelector, rawData => rawData.properties);
const namespacesSelector = createSelector(rawDataSelector, rawData => rawData.namespaces);

export const selectors = {
    namespaces: namespacesSelector,
    properties: propertiesSelector,
};

//
// Async action handlers (Epics, Sagas, etc)
//
