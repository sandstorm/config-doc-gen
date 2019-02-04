import {combineReducers} from 'redux';

import * as ConfigDocStore from './ConfigDocStore';

export interface IDataState {
  readonly ConfigDoc: ConfigDocStore.IConfigDocState;
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
