import {createStore, Store} from 'redux';
import {composeWithDevTools} from 'redux-devtools-extension';

import {ApplicationState, rootReducer} from './Store';

export default function configureStore(initialState?: ApplicationState): Store<ApplicationState> {
  const store = createStore(
    rootReducer,
    initialState || {},
    composeWithDevTools(
      //applyMiddleware(epicMiddleware)
    )
  );

  //epicMiddleware.run(rootEpic);

  return store;
}

