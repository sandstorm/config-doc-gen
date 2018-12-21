import { ActionsUnion, createAction } from '@martin_hotell/rex-tils';
import { createSelector } from 'reselect';



import { IApplicationState } from '..';
import { Accessibility } from "../../../Domain/Accessibility";
import { uiItemFromNamespace, uiItemFromProperty } from "../../../Domain/Ui/UiItem";
import * as dataStore from "../Data";


//
// State
//

export interface IUiState {
    readonly searchTerm: string;
    readonly sidebar: IUiSidebarState;
}

export interface IUiSidebarState {
    readonly filter: IUiSidebarFilter;
    readonly viewMode: UiSidebarViewMode;
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
    searchTerm: "",
    sidebar: {
        filter: {
            accessibilityFilter: [],
            nameFilter: "",
        },
        showQualifiedNames: true,
        viewMode: UiSidebarViewMode.DEFAULT,
    },
};

//
// Actions
//
export enum ActionTypes {
    CHANGE_SEARCH_TERM = '@@config-doc-ui/Ui/CHANGE_SEARCH_TERM',
    SELECT_SIDEBAR_VIEW_MODE = '@@config-doc-ui/Ui/SELECT_SIDEBAR_VIEW_MODE',
}

export const actions = {
    changeSearchTerm: (searchTerm: string) => createAction(ActionTypes.CHANGE_SEARCH_TERM, { searchTerm }),
    selectSidebarViewMode: (viewMode: UiSidebarViewMode) => createAction(ActionTypes.SELECT_SIDEBAR_VIEW_MODE, { viewMode }),
};

type UiAction = ActionsUnion<typeof actions>;

//
// Reducer
//
export function reducer(state: IUiState = initialState, action: UiAction): IUiState {
    const sidebar = state.sidebar;
    switch (action.type) {
        case ActionTypes.SELECT_SIDEBAR_VIEW_MODE:
            return {
                ...state,
                sidebar: {
                    ...sidebar,
                    viewMode: action.payload.viewMode
                }
            }
        case ActionTypes.CHANGE_SEARCH_TERM:
            return {
                ...state,
                searchTerm: action.payload.searchTerm
            }
        default:
            return state;
    }
}

//
// Selectors
//
const uiSelector = (state: IApplicationState) => state.ui.ConfigDoc;
const searchTermSelector = createSelector(
    uiSelector,
    ui => ui.searchTerm
)
const sidebarSelector = createSelector(
    uiSelector,
    ui => ui.sidebar
);
const sidebarViewModeSelector = createSelector(
    sidebarSelector,
    sidebar => sidebar.viewMode
);
const sidebarShowQualifiedNamesSelector = createSelector(
    sidebarSelector,
    sidebar => sidebar.showQualifiedNames
)

// route selectors
const routerSelector = (state: IApplicationState) => state.router;
const routerPathSelector = createSelector(
    routerSelector,
    router => {
        // tslint:disable-next-line:no-string-literal
        return router.location != null ? router.location['pathname'] : '/'
    }
)

// data filters
function filterName(name: string, searchTerm: string) {
    return name.toLowerCase().match(RegExp(searchTerm.toLowerCase())) != null;
}

const filteredNamespacesSelector = createSelector(
    searchTermSelector,
    dataStore.selectors.ConfigDoc.namespaces,
    (searchTerm, allNamespaces) => allNamespaces.filter(namespace => {
        return filterName(namespace.name, searchTerm);
    })
);

const filteredNamespaceUiItemsSelector = createSelector(
    filteredNamespacesSelector,
    (namespaces) => namespaces.map(uiItemFromNamespace)
);

const filteredPropertiesSelector = createSelector(
    searchTermSelector,
    dataStore.selectors.ConfigDoc.properties,
    (searchTerm, properties) => properties.filter(property => {
        return filterName(property.qualifiedName, searchTerm);
    })
);
const filteredPropertyUiItemsSelector = createSelector(
    filteredPropertiesSelector,
    sidebarShowQualifiedNamesSelector,
    (properties, showQualifiedNames) => properties.map(prop => uiItemFromProperty(prop, showQualifiedNames))
);

function findNamespaceFromRouterPath(routerPath: string) {
    const namespaceFromUrlResult = RegExp('/namespace/(.*)').exec(routerPath);
    if (!namespaceFromUrlResult || namespaceFromUrlResult.length <= 1) {
        return null;
    }
    return namespaceFromUrlResult[1];
}

function findPropertyFromRouterPath(routerPath: string) {
    const propertyFromUrlResult = RegExp('/property/(.*)').exec(routerPath);
    if (!propertyFromUrlResult || propertyFromUrlResult.length <= 1) {
        return null;
    }
    return propertyFromUrlResult[1];
}

const selectedNamespaceSelector = createSelector(
    dataStore.selectors.ConfigDoc.namespaces,
    dataStore.selectors.ConfigDoc.properties,
    routerPathSelector,
    (namespaces, properties, routerPath) => {
        let namespaceName = findNamespaceFromRouterPath(routerPath);
        if (!namespaceName) {
            const propertyName = findPropertyFromRouterPath(routerPath);
            if (!propertyName) {
                return null;
            }
            const property = properties.find(p => p.qualifiedName === propertyName)
            if (!property) {
                return null;
            }
            namespaceName = property.namespace;
        }
        return namespaces.find(namespace => namespace.name === namespaceName);
    }
);

const makeFilteredPropertiesByNamespaceSelector = () => createSelector(
    filteredPropertiesSelector,
    selectedNamespaceSelector,
    (properties, selectedNamespace) => properties
        .filter(property => selectedNamespace == null || property.namespace === selectedNamespace!.name)
        .map(prop => uiItemFromProperty(prop, false))
);

const selectedNamespaceUiItemSelector = createSelector(
    selectedNamespaceSelector,
    selectedNamespace => selectedNamespace ? uiItemFromNamespace(selectedNamespace) : null
)

const selectedPropertySelector = createSelector(
    dataStore.selectors.ConfigDoc.properties,
    routerPathSelector,
    (properties, routerPath) => {
        const propertyName = findPropertyFromRouterPath(routerPath);
        if (!propertyName) {
            return null;
        }
        return properties.find(p => p.qualifiedName === propertyName);
    }
);
const selectedPropertyUiItemSelector = createSelector(
    selectedPropertySelector,
    selectedProperty => selectedProperty ? uiItemFromProperty(selectedProperty, false) : null
)

export const selectors = {
    filteredNamespaceUiItems: filteredNamespaceUiItemsSelector,
    filteredNamespaces: filteredNamespacesSelector,
    filteredProperties: filteredPropertiesSelector,
    filteredPropertyUiItems: filteredPropertyUiItemsSelector,
    makeFilteredPropertiesByNamespace: makeFilteredPropertiesByNamespaceSelector,
    searchTerm: searchTermSelector,
    selectedNamespace: selectedNamespaceSelector,
    selectedNamespaceUiItem: selectedNamespaceUiItemSelector,
    selectedProperty: selectedPropertySelector,
    selectedPropertyUiItem: selectedPropertyUiItemSelector,
    sidebar: sidebarSelector,
    sidebarViewMode: sidebarViewModeSelector,
};

//
// Async action handlers (Epics, Sagas, etc)
//
