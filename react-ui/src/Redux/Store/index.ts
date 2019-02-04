import * as Data from './Data';
import * as Ui from './Ui';

import { connectRouter } from 'connected-react-router'
import { combineReducers } from 'redux'


export interface IApplicationState {
    readonly data: Data.IDataState;
    readonly ui: Ui.IUiState;
    readonly router: any;
  }
  
export const actions = {
    Data: Data.actions,
    Ui: Ui.actions,
};

export const selectors = {
    Data: Data.selectors,
    Ui: Ui.selectors,
};

export const reducers = (history: any) => combineReducers({
    data: Data.reducer,
    router: connectRouter(history),
    ui: Ui.reducer,
});
