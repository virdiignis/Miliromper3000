from django.contrib.auth.models import User
from django.http import JsonResponse
from rest_framework import viewsets

from dbAlco.serializers import UserGetSerializer, UserCreateSerializer


class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()

    def get_serializer_class(self):
        if self.action == 'create':
            return UserCreateSerializer
        else:
            return UserGetSerializer

    def list(self, request, *args, **kwargs):
        return JsonResponse(UserGetSerializer(request.user).data)
