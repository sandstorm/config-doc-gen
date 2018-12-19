import {createStore, Store} from 'redux';
import {composeWithDevTools} from 'redux-devtools-extension';

import {IApplicationState, rootReducer} from './Store';

export default function configureStore(initialState?: IApplicationState): Store<IApplicationState> {
  const store = createStore(
    rootReducer,
    initialState || {},
    composeWithDevTools(
      // applyMiddleware(epicMiddleware)
    )
  );

  // epicMiddleware.run(rootEpic);

  return store;
}

