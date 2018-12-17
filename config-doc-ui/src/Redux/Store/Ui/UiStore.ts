import {ActionsUnion, createAction} from '@martin_hotell/rex-tils';
import {createSelector} from 'reselect';

import {ApplicationState} from '..';
import {Accessibility} from "../../../Domain/Accessibility";
import {Property} from "../../../Domain/Property";
import {Namespace} from "../../../Domain/Namespace";

//
// State
//
export interface UiState {
    readonly sidebar: UiSidebarState;
    readonly selectedItem: Property | Namespace | null;
}

export interface UiSidebarState {
    readonly filter: UiSidebarFilter;
    readonly viewMode: UiSidebarViewMode;
}

export interface UiSidebarFilter {
    readonly nameFilter: string;
    readonly accessibilityFilter: ReadonlyArray<Accessibility>;
}

export enum UiSidebarViewMode {
    FLAT,
    GROUPED,
    TREE,
}

const initialState: UiState = {
    sidebar: {
        filter: {
            nameFilter: "",
            accessibilityFilter: []
        },
        viewMode: UiSidebarViewMode.FLAT
    },
    selectedItem: null
};

//
// Actions
//
export enum ActionTypes {
    SELECT_PROPERTY = '@@config-doc-ui/Ui/ConfigDoc/SELECT_PROPERTY',
    SELECT_NAMESPACE = '@@config-doc-ui/Ui/ConfigDoc/SELECT_NAMESPACE',
}

export const actions = {
    selectProperty: () => createAction(ActionTypes.SELECT_PROPERTY),
    selectNamespace: () => createAction(ActionTypes.SELECT_NAMESPACE),
};

type UiAction = ActionsUnion<typeof actions>;

//
// Reducer
//
export function reducer(state: UiState = initialState, action: UiAction): UiState {
    switch (action.type) {
        case ActionTypes.SELECT_PROPERTY:
        case ActionTypes.SELECT_NAMESPACE:
        default:
            return state;
    }
}

//
// Selectors
//
const uiSelector = (state: ApplicationState) => state.Ui.ConfigDoc;
const sidebarSelector = createSelector(
    uiSelector,
    ui => ui.sidebar
);
const sidebarViewModeSelector = createSelector(
    sidebarSelector,
    sidebar => sidebar.viewMode
);
const selectedItemSelector = createSelector(
    uiSelector,
    ui => ui.selectedItem
);

export const selectors = {
    sidebar: sidebarSelector,
    sidebarViewMode: sidebarViewModeSelector,
    selectedItem: selectedItemSelector,
};

//
// Async action handlers (Epics, Sagas, etc)
//
