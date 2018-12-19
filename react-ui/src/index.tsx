import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {Provider} from 'react-redux';

import './font-awesome';
import './index.css';

import configureStore from './Redux/configureStore';

import AppContainer from './Containers/AppContainer';

const store = configureStore();

ReactDOM.render(
    <Provider store={store}>
        <AppContainer />
    </Provider>,
    document.getElementById('root')
);
