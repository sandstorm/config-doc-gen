import * as React from 'react';
import * as ReactDOM from 'react-dom';

import NamespaceDoc from '.';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<NamespaceDoc />, div);
  ReactDOM.unmountComponentAtNode(div);
});
