import * as React from 'react';
import * as ReactDOM from 'react-dom';

import PropertyDoc from '.';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<PropertyDoc />, div);
  ReactDOM.unmountComponentAtNode(div);
});
