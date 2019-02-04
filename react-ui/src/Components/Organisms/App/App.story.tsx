import { storiesOf } from '@storybook/react';
import * as React from 'react';
import App from '.';

import { Accessibility } from '../../../Domain/Accessibility';

const namespace1 = {
  documentationContent: "Some <b>Namespace</b> documentation content<br/>that supports HTML",
  name: "foo.bar.namespace",
}

const property1 = {
    accessibility: Accessibility.IMPLEMENTATION,
    documentationContent: "Some <b>Property</b> documentation content<br/>that supports HTML",
    name: "fooPropertyStory",
    namespace: "foo.bar.namespace",
    qualifiedName: "foo.bar.namespace.fooPropertyStory",
};


storiesOf('App', module)
  .add('default', () => (
    <App />
  ))
  .add('with application name', () => (
    <App selectedNamespace={namespace1} selectedProperty={property1} />
  ));
