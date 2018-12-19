import {combineReducers} from 'redux';

import * as UiStore from './UiStore';

export interface IUiState {
    readonly ConfigDoc: UiStore.IUiState;
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
