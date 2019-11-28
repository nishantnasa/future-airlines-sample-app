Meta:

Narrative:
As a user
I want to generate the test offers for dummy ff members
So that I can verify the application stack performance and functionality


Scenario: Verify that a user is able to generate input files to trigger Engine
Given I generate the input and output files for engine run with auto-generated offers and frequent flyer members for NEW Code Repo


Scenario: Verify that a user is able to generate input files to trigger Engine
Meta:
@ignore
@pending
Given I generate the input and output files for engine run against existing offers for existing frequent flyer members for OLD Code Repo

Scenario: Verify that a user is able to generate input files to trigger Engine
Meta:
@ignore
@pending
Given I generate the input and output files for engine run with auto-generated offers and frequent flyer members for NEW Code Repo and delta extract with date 2018-11-30
