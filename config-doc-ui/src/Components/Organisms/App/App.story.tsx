import { storiesOf } from '@storybook/react';
import * as React from 'react';
import App from '.';

storiesOf('App', module)
  .add('default', () => (
    <App />
  ))
  .add('with application name', () => (
    <App configDocAppName="some application name" />
  ));
