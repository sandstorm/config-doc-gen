import {combineReducers} from 'redux';

import * as UiStore from './UiStore';

export interface UiState {
    readonly ConfigDoc: UiStore.UiState;
}

export const actions = {
    ConfigDoc: UiStore.actions,
};

export const selectors = {
    ConfigDoc: UiStore.selectors,
};

export const reducer = combineReducers({
    ConfigDoc: UiStore.reducer,
});
