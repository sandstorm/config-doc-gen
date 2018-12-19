import {action} from '@storybook/addon-actions';
import {storiesOf} from '@storybook/react';
import * as React from 'react';
import SidebarItemList from '.';
import {UiItemType} from "../../../Domain/Ui/UiItem";

const item1 = {
    identifier: "foo.prop1",
    label: "prop1",
    type: UiItemType.PROPERTY_API
};

const item2 = {
    identifier: "foo.prop2",
    label: "prop2",
    type: UiItemType.PROPERTY_IMPLEMENTATION
};

const item3 = {
    identifier: "foo.bar.namespace",
    label: "foo.bar.namespace",
    type: UiItemType.NAMESPACE
};

const item4 =  {
    identifier: "foo.unknown",
    label: "unknown type",
    type: UiItemType.UNKNOWN
};

const items = [item1, item2, item3, item4];

storiesOf('SidebarItemList', module)
    .add('default', () => (
        <SidebarItemList onClickItem={action('item clicked')}/>
    ))
    .add('with properties', () => (
        <SidebarItemList onClickItem={action('item clicked')} items={items}/>
    ))
    .add('with properties selected', () => (
        <SidebarItemList onClickItem={action('item clicked')} items={items} selectedItem={item1} />
    ));
