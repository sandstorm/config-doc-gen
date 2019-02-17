import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { Provider } from 'react-redux';

import { createBrowserHistory } from 'history'

import { ConnectedRouter } from 'connected-react-router'
import { Route } from 'react-router'

import { configureStore } from './Redux/configureStore';

import AppContainer from './Containers/AppContainer';

import './font-awesome';
import './index.css';

// Create a history of your choosing (we're using a browser history in this case)
const history = createBrowserHistory();
const store = configureStore(history);

const AppContainerRoute = () => (<AppContainer />);

ReactDOM.render(
  <Provider store={store}>
    <ConnectedRouter history={history}>
      <Route path="/" component={AppContainerRoute} />
    </ConnectedRouter>
  </Provider>,
  document.getElementById('root')
);
