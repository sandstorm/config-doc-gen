import {combineReducers} from 'redux';

import * as ConfigDocStore from './ConfigDocStore';

export interface DataState {
  readonly ConfigDoc: ConfigDocStore.ConfigDocState;
}

export const actions = {
  ConfigDoc: ConfigDocStore.actions,
};

export const selectors = {
  ConfigDoc: ConfigDocStore.selectors,
};

export const reducer = combineReducers({
  ConfigDoc: ConfigDocStore.reducer,
});
