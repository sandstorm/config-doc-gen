import {ActionsUnion, createAction} from '@martin_hotell/rex-tils';
import {createSelector} from 'reselect';

import {ApplicationState} from '..';
//import {employees} from '../../../Domain/mockData';
import {ConfigDoc} from "../../../Domain/ConfigDoc";

//
// State
//
export interface ConfigDocState {
    readonly rawApiData: ConfigDoc;
}

const initialState: ConfigDocState = {
    rawApiData: window["CONFIG_DOC_JSON_DATA"],
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
export function reducer(state: ConfigDocState = initialState, action: EmployeesAction): ConfigDocState {
    switch (action.type) {
        case ActionTypes.FETCH_CONFIG_DOC:
        default:
            return state;
    }
}

//
// Selectors
//
const rawDataSelector = (state: ApplicationState) => state.Data.ConfigDoc.rawApiData;
const propertiesSelector = createSelector(rawDataSelector, rawData => rawData.properties);
const namespacesSelector = createSelector(rawDataSelector, rawData => rawData.namespaces);

export const selectors = {
    properties: propertiesSelector,
    namespaces: namespacesSelector,
};

//
// Async action handlers (Epics, Sagas, etc)
//
