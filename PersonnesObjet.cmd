!create Roger : Personne
!set Roger.nom := 'Roger'
!set Roger.age := 22
!set Roger.sexe := #Homme

!create Simone : Personne
!set Simone.nom := 'Simone'
!set Simone.age := 45
!set Simone.sexe := #Femme

!create Linda : Personne
!set Linda.nom := 'Linda'
!set Linda.age := 15
!set Linda.sexe := #Femme

!create CompteRoger : Compte
!create CompteSimone : Compte

!set CompteRoger.solde := 1500
!set CompteSimone.solde := 2600

!insert (Roger,CompteRoger) into CompteBancaire
!insert (Simone,CompteSimone) into CompteBancaire

!create Banque : Entreprise 
!set Banque.nom := 'EntreprisBanque'
!set Banque.budget :=  12000
!set Banque.salaireEmploye := 2000

!insert (Roger,Banque) into Emploi
!insert (Simone,Banque) into Emploi

!insert (CompteRoger,Banque) into Paie
!insert (CompteSimone,Banque) into Paie

!create Universite : Entreprise
!set Universite.nom := 'Université'
!set Universite.budget := 7000
!set Universite.salaireEmploye := 1500

!insert (Linda,Universite) into Emploi