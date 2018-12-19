import * as React from 'react';
import * as ReactDOM from 'react-dom';
import AppSidebarDefaultView from '.';

it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<AppSidebarDefaultView/>, div);
    ReactDOM.unmountComponentAtNode(div);
});
