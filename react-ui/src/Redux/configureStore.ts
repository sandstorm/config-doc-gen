import {applyMiddleware, createStore, Store} from 'redux';
import {composeWithDevTools} from 'redux-devtools-extension';

import { routerMiddleware } from 'connected-react-router'

import { IApplicationState, reducers } from './Store';

export function configureStore(history: any): Store<IApplicationState> {
  const store = createStore(
    reducers(history),
    composeWithDevTools(
      applyMiddleware(routerMiddleware(history))
    )
  );

  // epicMiddleware.run(rootEpic);

  return store;
}

