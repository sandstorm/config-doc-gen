import {action} from '@storybook/addon-actions';
import {storiesOf} from '@storybook/react';

import * as React from 'react';

import AppHeader from '.';

import { UiItemType } from '../../../Domain/Ui/UiItem';


const namespace1 = {
    identifier: "foo.bar.namespace",
    label: "foo.bar.namespace",
    type: UiItemType.NAMESPACE
};

const prop1 = {
    identifier: "foo.bar.namespace.prop1",
    label: "prop1",
    type: UiItemType.PROPERTY_API
};

storiesOf('AppHeader', module)
    .add('default', () => (
        <AppHeader onSearchTermChanged={action('search term changed')} />
    ))
    .add('with search term and selected item', () => (
        <AppHeader searchTerm="some search term" selectedNamespace={namespace1} selectedProperty={prop1} onSearchTermChanged={action('search term changed')} />
    ))
;
