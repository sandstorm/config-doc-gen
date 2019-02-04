// import {action} from '@storybook/addon-actions';
import {storiesOf} from '@storybook/react';

import * as React from 'react';

import PropertyDoc from '.';

import { Accessibility } from '../../../Domain/Accessibility';

const property1 = {
    accessibility: Accessibility.IMPLEMENTATION,
    documentationContent: "<b>Some</b> documentation content<br/>that supports HTML",
    name: "fooPropertyStory",
    namespace: "foo.bar.namespace",
    qualifiedName: "foo.bar.namespace.fooPropertyStory",
};

storiesOf('PropertyDoc', module)
    .add('default', () => (
        <PropertyDoc />
    ))
    .add('with property (IMPLEMENTATION)', () => (
        <PropertyDoc property={property1} />
    ))
;
