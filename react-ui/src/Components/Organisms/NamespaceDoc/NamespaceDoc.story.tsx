// import {action} from '@storybook/addon-actions';
import {storiesOf} from '@storybook/react';

import * as React from 'react';

import NamespaceDoc from '.';

const namespace1 = {
    documentationContent: "Some <b>Namespace</b> documentation content<br/>that supports HTML",
    name: "foo.bar.namespace",
};

storiesOf('NamespaceDoc', module)
    .add('default', () => (
        <NamespaceDoc />
    ))
    .add('with namespace', () => (
        <NamespaceDoc namespace={namespace1} />
    ))
;
