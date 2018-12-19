import * as React from 'react';
import * as ReactDOM from 'react-dom';

import AppSidebar from '.';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<AppSidebar />, div);
  ReactDOM.unmountComponentAtNode(div);
});
