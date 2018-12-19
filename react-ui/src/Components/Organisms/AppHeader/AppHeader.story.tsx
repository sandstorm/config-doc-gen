import {action} from '@storybook/addon-actions';
import {storiesOf} from '@storybook/react';

import * as React from 'react';

import AppHeader from '.';

import { UiItemType } from '../../../Domain/Ui/UiItem';

const item1 = {
    identifier: "foo.prop1",
    label: "prop1",
    type: UiItemType.PROPERTY_API
};

storiesOf('AppHeader', module)
    .add('default', () => (
        <AppHeader onSearchTermChanged={action('search term changed')} />
    ))
    .add('with search term and selected item', () => (
        <AppHeader searchTerm="some search term" selectedItem={item1} onSearchTermChanged={action('search term changed')} />
    ))
;
