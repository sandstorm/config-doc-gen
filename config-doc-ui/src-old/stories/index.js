import React from 'react';

import '../font-awesome';

import {storiesOf} from '@storybook/react';
import {action} from '@storybook/addon-actions';
import {linkTo} from '@storybook/addon-links';


import SidebarPropertyList from '../components/molecules/SidebarPropertyList/SidebarPropertyList';
import SidebarNamespaceList from '../components/molecules/SidebarNamespaceList/SidebarNamespaceList';

storiesOf('Sidebar Namespace List', module)
    .add('with some namespaces',
        () =>
            <SidebarNamespaceList
                namespaces={[
                    'some.namespace.name',
                    'some.othernamespace.name',
                    'some.namespace.nested.name',
                    'some.mapNamespace.<KEY>.list.<INDEX>.nested'
                ]}
                onClickItem={action('Namespace List Item Clicked')}
            />
    );

const testProperties = [
    {
        name: 'someProperty',
        qualifiedName: 'some.namespace.name.someProperty',
        namespace: 'some.namespace.name',
        accessibility: 'API'
    },
    {
        name: 'otherProp',
        qualifiedName: 'some.namespace.name.otherProp',
        namespace: 'some.namespace.name',
        accessibility: 'IMPLEMENTATION'
    },
    {
        name: 'foo',
        qualifiedName: 'some.namespace.name.foo.foo',
        namespace: 'some.namespace.name.foo',
        accessibility: 'API'
    },
    {
        name: 'someNestedProperty',
        qualifiedName: 'some.namespace.<KEY>.name.<INDEX>.someProperty',
        namespace: 'some.namespace.<KEY>.name.<INDEX>',
        accessibility: 'API'
    }
];
storiesOf('Sidebar Property List', module)
    .add('with qualified names',
        () =>
            <SidebarPropertyList
                properties={testProperties}
                showQualifiedName={true}
                onClickItem={action('Property List Item Clicked')}
            />
    )
    .add('with simple names',
        () =>
            <SidebarPropertyList
                properties={testProperties}
                showQualifiedName={false}
                onClickItem={action('Property List Item Clicked')}
            />
    );


/*
storiesOf('Welcome', module).add('to Storybook', () => <Welcome showApp={linkTo('Button')} />);

storiesOf('Button', module)
  .add('with text', () => <Button onClick={action('clicked')}>Hello Button</Button>)
  .add('with some emoji', () => (
    <Button onClick={action('clicked')}>
      <span role="img" aria-label="so cool">
        😀 😎 👍 💯
      </span>
    </Button>
  ));
*/
