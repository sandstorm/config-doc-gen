import {ActionsUnion, createAction} from '@martin_hotell/rex-tils';
import {createSelector} from 'reselect';

import {IApplicationState} from '..';
import {Accessibility} from "../../../Domain/Accessibility";
import {UiItem, uiItemFromNamespace, uiItemFromProperty} from "../../../Domain/Ui/UiItem";
import * as dataStore from "../Data";

//
// State
//

export interface IUiState {
    readonly sidebar: IUiSidebarState;
}

export interface IUiSidebarState {
    readonly filter: IUiSidebarFilter;
    readonly viewMode: UiSidebarViewMode;
    readonly selectedItem: UiItem | null;
    readonly showQualifiedNames: boolean;
}

export interface IUiSidebarFilter {
    readonly nameFilter: string;
    readonly accessibilityFilter: ReadonlyArray<Accessibility>;
}

export enum UiSidebarViewMode {
    DEFAULT,
    FLAT,
    GROUPED,
    TREE,
}

export function allSidebarViewModes(): ReadonlyArray<UiSidebarViewMode> {
    return Object.keys(UiSidebarViewMode).filter(viewModeKey => !isNaN(Number(UiSidebarViewMode[viewModeKey]))).map(viewModeKey => UiSidebarViewMode[viewModeKey])
}

const initialState: IUiState = {
    sidebar: {
        filter: {
            accessibilityFilter: [],
            nameFilter: "",
        },
        selectedItem: null,
        showQualifiedNames: true,
        viewMode: UiSidebarViewMode.DEFAULT,
    },
};

//
// Actions
//
export enum ActionTypes {
    SELECT_SIDEBAR_ITEM = '@@config-doc-ui/Ui/SELECT_SIDEBAR_ITEM',
    SELECT_SIDEBAR_VIEW_MODE = '@@config-doc-ui/Ui/SELECT_SIDEBAR_VIEW_MODE',
}

export const actions = {
    selectSidebarItem: (item: UiItem) => createAction(ActionTypes.SELECT_SIDEBAR_ITEM, {item}),
    selectSidebarViewMode: (viewMode: UiSidebarViewMode) => createAction(ActionTypes.SELECT_SIDEBAR_VIEW_MODE, {viewMode})
};

type UiAction = ActionsUnion<typeof actions>;

//
// Reducer
//
export function reducer(state: IUiState = initialState, action: UiAction): IUiState {
    const sidebar = state.sidebar;
    switch (action.type) {
        case ActionTypes.SELECT_SIDEBAR_ITEM:
            return {
                ... state,
                sidebar: {
                    ... sidebar,
                    selectedItem: action.payload.item
                }
            }
        case ActionTypes.SELECT_SIDEBAR_VIEW_MODE:
            return {
                ... state,
                sidebar: {
                    ... sidebar,
                    viewMode: action.payload.viewMode
                }
            }
        default:
            return state;
    }
}

//
// Selectors
//
const uiSelector = (state: IApplicationState) => state.Ui.ConfigDoc;
const sidebarSelector = createSelector(
    uiSelector,
    ui => ui.sidebar
);
const sidebarViewModeSelector = createSelector(
    sidebarSelector,
    sidebar => sidebar.viewMode
);
const selectedItemSelector = createSelector(
    sidebarSelector,
    sidebar => sidebar.selectedItem
);
const sidebarShowQualifiedNamesSelector = createSelector(
    sidebarSelector,
    sidebar => sidebar.showQualifiedNames
)

// data filters
const filteredNamespacesSelector = createSelector(
    dataStore.selectors.ConfigDoc.namespaces,
    allNamespaces => allNamespaces.filter(namespace => {
        return true;
    }).map(uiItemFromNamespace)
);

const filteredPropertiesSelector = createSelector(
    dataStore.selectors.ConfigDoc.properties,
    sidebarShowQualifiedNamesSelector,
    (allProperties, showQualifiedNames) => allProperties.filter(property => {
        return true;
    }).map(prop => uiItemFromProperty(prop, showQualifiedNames))
);

export const selectors = {
    filteredNamespaces: filteredNamespacesSelector,
    filteredProperties: filteredPropertiesSelector,
    sidebar: sidebarSelector,
    sidebarSelectedItem: selectedItemSelector,
    sidebarViewMode: sidebarViewModeSelector,
};

//
// Async action handlers (Epics, Sagas, etc)
//
