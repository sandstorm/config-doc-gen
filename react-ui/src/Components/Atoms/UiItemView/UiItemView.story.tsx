import { storiesOf } from '@storybook/react';
import * as React from 'react';
import UiItemView from '.';
import { UiItemType } from "../../../Domain/Ui/UiItem";

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

storiesOf('UiItemView', module)
    .add('Property (API) - Color Scheme', () => (
        <div className="main-color-scheme">
            <UiItemView item={item1} />
        </div>
    ))
    .add('Property (IMPLEMENTATION) - Color Scheme', () => (
        <div className="main-color-scheme">
            <UiItemView item={item2} />
        </div>
    ))
    .add('Namespace - Color Scheme', () => (
        <div className="main-color-scheme">
            <UiItemView item={item3} />
        </div>
    ))
    .add('Unknown - Color Scheme', () => (
        <div className="main-color-scheme">
            <UiItemView item={item4} />
        </div>
    ))
    .add('Property (API)', () => (
        <UiItemView item={item1} />
    ))
    .add('Property (IMPLEMENTATION)', () => (
        <UiItemView item={item2} />
    ))
    .add('Namespace', () => (
        <UiItemView item={item3} />
    ))
    .add('Unknown', () => (
        <UiItemView item={item4} />
    ));
