import { ActionsUnion, createAction } from '@martin_hotell/rex-tils';
import { createSelector } from 'reselect';
import { IApplicationState } from '..';
import { ConfigDoc } from "../../../Domain/ConfigDoc";
import { HowToFeature } from '../../../Domain/HowToFeature';

import packageJson from '../../../../package.json';
import version from '../../../VERSION.json';
//
// State
//
export interface IConfigDocState {
    readonly rawApiData: ConfigDoc;
    readonly uiVersion: string;
}

function readVersionFromPackageJson() {
    return packageJson.version + " #" + version.version;
}

const initialState: IConfigDocState = {
    // tslint:disable-next-line:no-string-literal
    rawApiData: {... window["CONFIG_DOC_JSON_DATA"]},
    uiVersion: "UI: " + readVersionFromPackageJson(),
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
const rawDataSelector = (state: IApplicationState) => state.data.ConfigDoc.rawApiData;
const propertiesSelector = createSelector(rawDataSelector, rawData => rawData.properties);
const namespacesSelector = createSelector(rawDataSelector, rawData => rawData.namespaces);
const moduleNameSelector = createSelector(rawDataSelector, rawData => rawData.moduleName);
const moduleVersionSelector = createSelector(rawDataSelector, rawData => rawData.moduleVersion);
const showSpringHowToSelector = createSelector(rawDataSelector, rawData => rawData.howToFeatures.some((it) => it === HowToFeature.SPRING));

const versionsSelector = (state: IApplicationState) => [
    state.data.ConfigDoc.rawApiData.generatorVersion.processorVersion,
    state.data.ConfigDoc.rawApiData.generatorVersion.coreVersion,
    state.data.ConfigDoc.rawApiData.generatorVersion.annotationsVersion,
    state.data.ConfigDoc.uiVersion
];

export const selectors = {
    moduleName: moduleNameSelector,
    moduleVersion: moduleVersionSelector,
    namespaces: namespacesSelector,
    properties: propertiesSelector,
    showSpringHowTo: showSpringHowToSelector,
    versions: versionsSelector
};

//
// Async action handlers (Epics, Sagas, etc)
//
