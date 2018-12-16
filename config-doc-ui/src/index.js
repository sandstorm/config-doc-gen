import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from "react-redux";
import './index.scss';
import AppContainer from './containers/AppContainer';
import state from './state';
import './font-awesome';

ReactDOM.render(
    <Provider store={state}>
        <AppContainer />
    </Provider>,
    document.getElementById('root')
);
